Cinema - Exercise 00

WHAT IT DOES:
- Sign up new users
- Sign in with email/password
- View profile page
- Logout

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
- /signUp - Register new user
- /signIn - Login
- /profile - User profile (must be logged in)
- /logout - Logout