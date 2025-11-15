Cinema - Exercise 02

WHAT'S NEW:
- Profile page now uses JSP
- Shows login history (when and from where you logged in)
- Upload images
- View your uploaded images

HOW TO RUN:
1. Create database:
   sudo -u postgres createdb cinema

2. Create tables:
   sudo -u postgres psql -d cinema -f src/main/resources/sql/schema.sql

3. Create upload folder:
   sudo mkdir -p /var/lib/tomcat10/uploads
   sudo chown tomcat10:tomcat10 /var/lib/tomcat10/uploads

4. Build:
   mvn clean package

5. Deploy:
   cp target/Cinema.war /var/lib/tomcat10/webapps/

6. Open browser:
   http://localhost:8080/Cinema/

PAGES:
- /signUp - Register
- /signIn - Login
- /profile - See your info, login history, and upload images
- /logout - Logout