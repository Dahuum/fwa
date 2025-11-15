Cinema - Exercise 01

WHAT'S NEW:
- Authentication filter protects pages
- Can't access profile without login (403 error)
- Already logged in? Can't go to signUp/signIn

HOW TO RUN:
1. Create database:
   sudo -u postgres createdb cinema

2. Create tables:
   sudo -u postgres psql -d cinema -f src/main/resources/sql/schema.sql

3. Build:
   mvn clean package

4. Deploy:
   cp target/Cinema.war /var/lib/tomcat10/webapps/

5. Open browser:
   http://localhost:8080/Cinema/

PAGES:
Same as Exercise 00