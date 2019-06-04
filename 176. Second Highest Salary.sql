SELECT
    IFNULL(
      (SELECT DISTINCT Salary
       FROM Employee
       ORDER BY Salary DESC
        LIMIT 1 OFFSET 1),
    NULL) AS SecondHighestSalary
--Ifnull: similar to getOrDefualt. Limit 1: output 1 line, offset 1: ignore first line
