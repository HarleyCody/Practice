CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
DECLARE M INT;
SET M=N-1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT DISTINCT Salary FROM Employee order by Salary DESC LIMIT 1 OFFSET M
  );
END

-- initial variable by key word DECLARE Transfer value by key word SET, semicolon ';' is needed after sentence--
