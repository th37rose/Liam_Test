INSERT INTO students (id, first_name, last_name, email, creation_timestamp, update_timestamp) VALUES
    (1, 'John', 'dave', 'john@email.com', 1499070300, 1499070300),
    (2, 'Jane', 'test', 'jane@email.com', 1499070300, 1499070300);

INSERT INTO courses (id, course_name, course_description,credit_hours, max_students, creation_timestamp, update_timestamp) VALUES
    (1, 'Mathematics', '', 3, 30, 1499070300, 1499070300),
    (2, 'History', '', 4, 25, 1499070300, 1499070300);

INSERT INTO lectures (id, lecture_name, field_of_study, course_id, student_id, creation_timestamp, update_timestamp) VALUES
    (1, 'Algebra', 'Mathematics', 1, 1, 1499070300, 1499070300),
    (2, 'World War II', 'History', 2, 1, 1499070300, 1499070300);

