CREATE TABLE notas (
   id INT AUTO_INCREMENT PRIMARY KEY,
   matricula_id INT,
   disciplina_id INT,
   nota DECIMAL(5,2),
   data_lancamento DATE,
   FOREIGN KEY (matricula_id) REFERENCES matriculas(id),
   FOREIGN KEY (disciplina_id) REFERENCES disciplinas(id)
);