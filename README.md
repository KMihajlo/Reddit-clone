# Reddit Clone

## Development

---
### BE
* Java 11
* Spring Framework 5.0 & Spring Boot 2.0
* Spring Data JPA
* Spring Security
* Hibernate
* Maven 3

### FE
* Tymeleaf (Templating engine)
* HTML / CSS3
* Bootstrap
* JavaScript

### Databases
* H2 in - memory
* MySQL

---

&nbsp;&nbsp; `list.html` - Initial frontpage

* created `main_layout` page, which is reused on every page
* made the application responsive using media queries

  <table>
  <tr>
    <td>Desktop view</td>
     <td>Mobile view</td>
  </tr>
  <tr>
    <td><img alt="DesktopView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/desktopview.png"/></td>
    <td><img alt="MobileView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/mobileview.png"/></td>
  </tr>
 </table>

`view.html` - View of a single link with comments
* used PrettyTime to convert the date
* implemented voting functionality
* if not signed in, you will not be able to post a comment or a link
<div>
    <div>
        <p style="font-style: italic"></p>
        <img alt="SampleLink" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/sampleLink.png"/>
    </div>
</div>

`register.html` - Register page
<table>
  <tr>
    <td>Frontend validation</td>
     <td>Backend validation</td>
  </tr>
  <tr>
    <td><img alt="FrontendValidation" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/frontend_validation.png"/></td>
    <td><img alt="BackendValidation" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/backend_validation.png"/></td>
  </tr>
 </table>

`activation.html` & `welcome.html` - Manually created email templates
* tested using Mailtrap
<br>
<table>
  <tr>
    <td>Activation Email - is being sent when registered for the first time</td>
     <td>Welcome Email - is being sent when you activate your account</td>
  </tr>
  <tr>
    <td><img alt="ActivationEmail" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/activation_email.png"/></td>
    <td><img alt="WelcomeEmail" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/welcome_email.png"/></td>
  </tr>
 </table>

`profile.html` - Profile page for every user
<div>
    <div>
        <p style="font-style: italic"></p>
        <img alt="DesktopView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/profile_page.png"/>
    </div>
</div>

***in progress...***

