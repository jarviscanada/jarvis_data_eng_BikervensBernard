-- 1. Group hosts by hardware info --
-- Group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group
SELECT cpu_number, id, total_mem
FROM host_info
ORDER BY MAX(cpu_number) OVER (
    PARTITION BY cpu_number
    ORDER BY total_mem DESC
);

-- 2. Average memory usage --
-- 5 mins interval for each host. (used memory = total memory - free memory)
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

-- Temp table to organize steps a little; round the timestamps first
CREATE TEMP TABLE host_usage_rounded AS
SELECT host_id, memory_free, timestamp, round5(timestamp) as rounded_time
FROM host_usage;

-- Average memory used over 5 min (usage)
SELECT hur.host_id, hin.hostname, hur.rounded_time,
       CEILING(AVG((hin.total_mem - hur.memory_free)*1.0 / hin.total_mem)*100) AS avg_used_mem_percentage
FROM host_info hin INNER JOIN host_usage_rounded hur ON hin.id = hur.host_id
GROUP BY hur.host_id, hin.hostname, hur.rounded_time
ORDER BY hur.host_id, hur.rounded_time;

-- 3. Detect host failure --
-- Query for finding failed hosts
SELECT DISTINCT ON (hur.host_id) hur.host_id, hur.rounded_time as rounded_time, COUNT(*) as num_data_points
FROM host_usage_rounded hur
GROUP BY hur.host_id, hur.rounded_time
HAVING COUNT(*) < 3;
