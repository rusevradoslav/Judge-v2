# Judge-v2
 Judge V2 is an application, which organizes the exercises in the course. 
 In the courses, each homework is required to be in a zip format.
 The application will have an admin functionality. Only the administrators can add new exercises and give out admin roles to other users. 
 The rest of the users can submit solutions to the specific exercise.

**Technologies:**

**Front-End:**

*   HTML
*   CSS
*   Bootstrap
*   Thymeleaf

 
**Back-End:**
*   Java
*   Spring Boot
*   Spring MVC
*   MySQL Database
*   JPA

 **Without authentication** you can see : 

  - the index page
  - the login page
  - the user registration form

**Users** can : 

   - see their profile page
   - add homework
   - check homework


**Admins** can : 

   - see their profile page
   - add homework
   - check homework
   - add exercise
   - change user role
   
### Routes

URLs | Description
---------|---------
 */* | Index page - login, register
 */users/register* | Register page -  page where user can register .
 */users/login* | Login page - page where user can login .
 */users/profile/{id}* | Profile page - page where user can see user profile details.
 */roles/add* | Roles change - page where admin can change user role.
 */homeworks/add* | Homeworks page - page where user can add homework.
 */homeworks/check* | Homework check page - page where user can check other users homeworks.
 
 
 Database Diagram Screenshot
 ---
 
 ![database](/src/main/resources/static/screens/database.png)
 
Website Screenshots
---

- Index Page 
![homepage](/src/main/resources/static/screens/index.png)

- Register Page
![addons](/src/main/resources/static/screens/register.png)

- Login Page
![pending-addons](/src/main/resources/static/screens/login.png)

- Home Admin Page
![product-page](/src/main/resources/static/screens/admin-page.png)

- Home User Page
![product-page](/src/main/resources/static/screens/user-page.png)

- Profile Page
![product-page](/src/main/resources/static/screens/user-profile.png)

- Exercise Add Page
![create-new-addon](/src/main/resources/static/screens/add-exercise.png)

- Change Role Page
![update](/src/main/resources/static/screens/change-role.png)

- Homework Add Page
![create-new-addon](/src/main/resources/static/screens/add-homework.png)

- Homework Check Page
![update](/src/main/resources/static/screens/check-homework.png)




 
