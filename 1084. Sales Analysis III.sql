# Write your MySQL query statement below
Select Product.product_id, product_name from Product where Product.product_id not in (Select product_id from Sales 
where Sales.sale_date < "2019-01-01" OR Sales.sale_date > "2019-03-31")
