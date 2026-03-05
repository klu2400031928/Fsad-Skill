# Hibernate HQL Skill 3 - Sorting, Pagination & Aggregates

## Overview
This project demonstrates advanced HQL operations including:
- Sorting (ASC/DESC)
- Pagination
- Aggregate functions (COUNT, MIN, MAX, AVG, SUM)
- GROUP BY operations
- Filtering with WHERE
- Pattern matching with LIKE

## Technologies
- Java 11+
- Hibernate 5.x
- MySQL
- Maven

## Project Structure
```
hibernate-hql-skill3/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── inventory/
│   │   │           ├── entity/
│   │   │           │   └── Product.java
│   │   │           ├── util/
│   │   │           │   └── HibernateUtil.java
│   │   │           ├── loader/
│   │   │           │   └── ProductDataLoader.java
│   │   │           └── demo/
│   │   │               └── HQLDemo.java
│   │   └── resources/
│   │       └── hibernate.cfg.xml
├── pom.xml
├── .gitignore
└── README.md
```

## Setup
1. Ensure MySQL is running with the `inventory_db` database
2. Update database credentials in `src/main/resources/hibernate.cfg.xml` if needed
3. Run `ProductDataLoader` to populate sample data:
   ```
   mvn exec:java -Dexec.mainClass="com.inventory.loader.ProductDataLoader"
   ```
4. Run the demo:
   ```
   mvn exec:java -Dexec.mainClass="com.inventory.demo.HQLDemo"
   ```

## Tasks Completed
✅ Product entity setup  
✅ Sample data insertion (8 products)  
✅ Sorting by price (ASC/DESC)  
✅ Sorting by quantity  
✅ Pagination implementation  
✅ Aggregate operations (COUNT, MIN, MAX, AVG, SUM)  
✅ GROUP BY queries  
✅ Price range filtering  
✅ LIKE pattern matching  

## Sample Products
| Name       | Price   | Quantity | Description |
|------------|---------|----------|-------------|
| Laptop     | 899.99  | 15       | Electronics |
| Mouse      | 25.50   | 50       | Electronics |
| Keyboard   | 45.00   | 30       | Electronics |
| Monitor    | 299.99  | 20       | Electronics |
| Desk Chair | 150.00  | 0        | Furniture   |
| Desk Lamp  | 35.75   | 25       | Furniture   |
| Notebook   | 5.99    | 100      | Stationery  |
| Pen Set    | 12.50   | 75       | Stationery  |

## HQL Operations Demonstrated

### Sorting
- **Ascending**: `FROM Product p ORDER BY p.price ASC`
- **Descending**: `FROM Product p ORDER BY p.price DESC`
- **By Quantity**: `FROM Product p ORDER BY p.quantity DESC`

### Pagination
- **First 3**: `query.setFirstResult(0).setMaxResults(3)`
- **Next 3**: `query.setFirstResult(3).setMaxResults(3)`

### Aggregates
- **Count**: `SELECT COUNT(p) FROM Product p`
- **Min/Max**: `SELECT MIN(p.price), MAX(p.price) FROM Product p`
- **Average**: `SELECT AVG(p.price) FROM Product p`
- **Sum**: `SELECT SUM(p.quantity) FROM Product p`

### Filtering
- **Price Range**: `WHERE p.price BETWEEN :minPrice AND :maxPrice`
- **LIKE Patterns**:
  - Starts with: `WHERE p.name LIKE 'D%'`
  - Ends with: `WHERE p.name LIKE '%p'`
  - Contains: `WHERE p.name LIKE '%Desk%'`

### Grouping
- **GROUP BY**: `SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description`

## Author
Venkataguna Srivardhan

