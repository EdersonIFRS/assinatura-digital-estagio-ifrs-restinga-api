
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
) AS dados
WHERE NOT EXISTS (
  SELECT 1
  FROM curso
  WHERE curso.id = dados.id
    AND curso.nome_curso = dados.nome_curso
);
