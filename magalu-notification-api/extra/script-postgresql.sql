-- Criar a tabela base tb_notification
CREATE TABLE tb_notification (
    id SERIAL PRIMARY KEY,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    scheduled_date_time TIMESTAMP NOT NULL,
    sent_date_time TIMESTAMP,
    receiver VARCHAR(60) NOT NULL,
    message VARCHAR(200) NOT NULL,
    PRIMARY KEY (id)    
);

-- Criar a tabela para NotificationType
CREATE TABLE tb_notification_type (
    id SERIAL PRIMARY KEY,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name VARCHAR(60) NOT NULL,
    notification_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (notification_id) REFERENCES tb_notification (id) ON DELETE CASCADE
);
