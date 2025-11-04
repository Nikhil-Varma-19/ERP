ALTER TABLE error_logger
ADD COLUMN trace_id UUID NOT NULL;

ALTER TABLE api_logger
ADD COLUMN  token TEXT ;

ALTER TABLE api_logger
DROP COLUMN user_id;