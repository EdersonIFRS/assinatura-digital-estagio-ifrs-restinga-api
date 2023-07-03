
INSERT INTO curso (id, nome_curso)
SELECT dados.id, dados.nome_curso
FROM (
  SELECT '10' AS id, 'Análise e Desenvolvimento de Sistemas' AS nome_curso
  UNION ALL
  SELECT '11', 'Letras Português e Espanhol'
  UNION ALL
  SELECT '12', 'Eletrônica Industrial'
  UNION ALL
  SELECT '13', 'Gestão Desportiva e de Lazer'
  UNION ALL
  SELECT '14', 'Processos Gerenciais'
  UNION ALL
  SELECT '15', 'Setor Estágio'
) AS dados
WHERE NOT EXISTS (
  SELECT 1
  FROM curso
  WHERE curso.id = dados.id
    AND curso.nome_curso = dados.nome_curso
);


INSERT INTO roles (id,name)
SELECT dados.id, dados.name
FROM (
         SELECT '1' AS id, 'ROLE_ALUNO' AS name
         UNION ALL
         SELECT '2', 'ROLE_SERVIDOR'
         UNION ALL
         SELECT '3', 'ROLE_SESTAGIO'
         UNION ALL
         SELECT '4', 'ROLE_DIRETOR'
     ) AS dados
WHERE NOT EXISTS (
        SELECT 1
        FROM roles
        WHERE roles.id = dados.id
          AND roles.name = dados.name
    );
