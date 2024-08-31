CREATE OR REPLACE FUNCTION public.get_emergency_counts(start_date date, end_date date)
 RETURNS TABLE(emergency_new integer, emergency_pending integer, emergency_attendance integer)
 LANGUAGE plpgsql
AS $function$
BEGIN
    RETURN QUERY
    SELECT
        SUM(CASE WHEN estado_id = 1 THEN 1 ELSE 0 END)::INTEGER AS emergency_new,
        SUM(CASE WHEN estado_id = 2 THEN 1 ELSE 0 END)::INTEGER AS emergency_pending,
        SUM(CASE WHEN estado_id = 3 THEN 1 ELSE 0 END)::INTEGER AS emergency_attendance
    FROM reporte_emergencia
    WHERE fecha_hora_reporte BETWEEN start_date AND end_date;
END;
$function$
;
