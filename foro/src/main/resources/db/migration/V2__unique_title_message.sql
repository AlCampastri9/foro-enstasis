ALTER TABLE topics
ADD CONSTRAINT uq_topics_title_message UNIQUE (title, message);
