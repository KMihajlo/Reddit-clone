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
`list.html` - Initial frontpage
* created `main_layout` page, which is reused on every page
* made the application responsive using media queries
<div style="display: flex; justify-content: space-between">
    <div>
        <p style="font-style: italic">Desktop</p>
        <img alt="DesktopView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/desktopview.png"/>
    </div>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <div>
        <p style="font-style: italic">Mobile</p>
        <img alt="MobileView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/mobileview.png"/>
    </div>
</div>

`view.html` - View of a single link with comments
* used PrettyTime to convert the date
* implemented voting functionality
* if not signed in, you will not be able to post a comment or a link
<div>
    <div>
        <p style="font-style: italic"></p>
        <img alt="DesktopView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/sampleLink.png"/>
    </div>
</div>

`register.html` - Register page
<div style="display: flex; justify-content: space-between">
    <div>
        <p style="font-style: italic">Frontend Validation</p>
        <img alt="DesktopView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/frontend_validation.png"/>
    </div>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <div>
        <p style="font-style: italic">Backend Validation</p>
        <img alt="MobileView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/backend_validation.png"/>
    </div>
</div>

`activation.html` & `welcome.html` - Manually created email templates
* tested using Mailtrap
<div style="display: flex; justify-content: space-between">
    <div>
        <p style="font-style: italic">Activation Email - is being sent when registered for the first time</p>
        <img alt="DesktopView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/activation_email.png"/>
    </div>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <div>
        <p style="font-style: italic">Welcome Email - is being sent when you activate your account</p>
        <img alt="MobileView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/welcome_email.png"/>
    </div>
</div>

`profile.html` - Profile page for every user
<div>
    <div>
        <p style="font-style: italic"></p>
        <img alt="DesktopView" src="https://github.com/KMihajlo/Reddit-clone/blob/master/src/main/resources/static/images/profile_page.png"/>
    </div>
</div>

***in progress...***

