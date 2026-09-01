#  Job Portal System

A full-stack Java web application that connects **Job Seekers** and **Recruiters** through a centralized job-search and recruitment platform.

The application provides role-based workflows for creating professional profiles, searching for jobs, posting vacancies, applying for jobs, managing job postings, and tracking applications.

---

##  Project Overview

The **Job Portal System** is a web-based recruitment platform designed to simulate a real-world job portal.

The system provides separate workflows for:

-  Job Seekers
-  Recruiters

Job Seekers can create and manage their professional profiles, search for suitable job opportunities, apply for jobs, and track their applications.

Recruiters can create company profiles, post job openings, manage job listings, view applicants, review candidate profiles, and update application statuses.

The project follows a layered architecture using **Java, Struts 2, JSP, PostgreSQL, JDBC, Maven, and Apache Tomcat**.

---

##  Features

###  Job Seeker

- User registration and login
- Logout and session handling
- Candidate profile management
- Education management
- Experience management
- Skills management
- Job search
- View available job opportunities
- Apply for jobs
- View submitted applications
- Track application status

###  Recruiter

- Recruiter registration and login
- Company profile management
- Recruiter dashboard
- Post new job openings
- Manage posted jobs
- Delete job postings
- View applicants
- View candidate profiles
- Update application status

### Application Management

- Job application submission
- Applicant tracking
- Recruiter-side applicant management
- Candidate-side application tracking
- Application status updates

---

##  Technology Stack

| Category | Technology |
|----------|------------|
| Programming Language | Java |
| Web Framework | Struts 2 |
| Frontend | JSP, HTML, CSS |
| Database | PostgreSQL |
| Database Connectivity | JDBC |
| Architecture | MVC / Layered Architecture |
| Data Access | DAO Pattern |
| Build Tool | Maven |
| Application Server | Apache Tomcat |
| Version Control | Git |
| Repository Hosting | GitHub |
| IDE | Eclipse |

---

##  Architecture

The application follows a layered architecture that separates the presentation layer, request handling, data access, model objects, and database connectivity.

text
                         ┌──────────────────────┐
                         │       Browser        │
                         │     HTML / JSP       │
                         └──────────┬───────────┘
                                    │
                                    ▼
                         ┌──────────────────────┐
                         │       Struts 2       │
                         │       Actions        │
                         └──────────┬───────────┘
                                    │
                                    ▼
                         ┌──────────────────────┐
                         │      DAO Layer       │
                         │  Database Operations │
                         └──────────┬───────────┘
                                    │
                                    ▼
                         ┌──────────────────────┐
                         │      PostgreSQL      │
                         │       Database       │
                         └──────────────────────┘

##Project Structure
JobPortalSystem/
│
├── src/
│   └── main/
│       │
│       ├── java/
│       │   └── com/
│       │       └── jobportal/
│       │           │
│       │           ├── action/
│       │           │   ├── ApplyJobAction.java
│       │           │   ├── CandidateProfileAction.java
│       │           │   ├── CompanyProfileAction.java
│       │           │   ├── DashboardAction.java
│       │           │   ├── DatabaseTestAction.java
│       │           │   ├── DeleteJobAction.java
│       │           │   ├── EducationAction.java
│       │           │   ├── ExperienceAction.java
│       │           │   ├── HelloAction.java
│       │           │   ├── JobAction.java
│       │           │   ├── JobSearchAction.java
│       │           │   ├── LoginAction.java
│       │           │   ├── LogoutAction.java
│       │           │   ├── ManageJobsAction.java
│       │           │   ├── MyApplicationsAction.java
│       │           │   ├── PostJobAction.java
│       │           │   ├── RecruiterCandidateProfileAction.java
│       │           │   ├── RegisterAction.java
│       │           │   ├── SkillAction.java
│       │           │   ├── UpdateApplicationStatusAction.java
│       │           │   └── ViewApplicantsAction.java
│       │           │
│       │           ├── dao/
│       │           │   ├── CandidateProfileDAO.java
│       │           │   ├── CompanyProfileDAO.java
│       │           │   ├── EducationDAO.java
│       │           │   ├── ExperienceDAO.java
│       │           │   ├── JobApplicationDAO.java
│       │           │   ├── JobDAO.java
│       │           │   └── SkillDAO.java
│       │           │
│       │           ├── model/
│       │           │   ├── Applicant.java
│       │           │   ├── CandidateProfile.java
│       │           │   ├── CompanyProfile.java
│       │           │   ├── Education.java
│       │           │   ├── Experience.java
│       │           │   ├── Job.java
│       │           │   ├── JobApplication.java
│       │           │   ├── MyApplication.java
│       │           │   └── Skill.java
│       │           │
│       │           └── util/
│       │               └── DBConnection.java
│       │
│       ├── resources/
│       │   └── struts.xml
│       │
│       └── webapp/
│           │
│           ├── WEB-INF/
│           │   └── web.xml
│           │
│           ├── css/
│           │   └── style.css
│           │
│           ├── candidateProfile.jsp
│           ├── companyProfile.jsp
│           ├── database-test.jsp
│           ├── education.jsp
│           ├── experience.jsp
│           ├── hello.jsp
│           ├── index.jsp
│           ├── jobPosted.jsp
│           ├── jobSearch.jsp
│           ├── login.jsp
│           ├── loginSuccess.jsp
│           ├── manageJobs.jsp
│           ├── myApplications.jsp
│           ├── postJob.jsp
│           ├── recruiterDashboard.jsp
│           ├── register.jsp
│           ├── registrationSuccess.jsp
│           ├── seekerDashboard.jsp
│           ├── skills.jsp
│           └── viewApplicants.jsp
│
├── pom.xml
├── .gitignore
└── README.md

###Application Workflow
###Job Seeker Workflow
Register
   │
   ▼
Login
   │
   ▼
Candidate Dashboard
   │
   ├── Candidate Profile
   │       ├── Education
   │       ├── Experience
   │       └── Skills
   │
   ▼
Search Jobs
   │
   ▼
View Job Details
   │
   ▼
Apply for Job
   │
   ▼
My Applications
   │
   ▼
Track Application Status
##Recruiter Workflow
Register
   │
   ▼
Login
   │
   ▼
Recruiter Dashboard
   │
   ├── Company Profile
   │
   ├── Post Job
   │
   ├── Manage Jobs
   │
   └── Delete Job
   │
   ▼
View Applicants
   │
   ▼
View Candidate Profile
   │
   ▼
Update Application Status


# Main Modules
# Authentication Module
Responsible for:
User registration
User login
User logout
Session-based authentication
Role-based navigation

##Candidate Profile Module
Allows job seekers to manage their professional information.
Includes:
Personal profile
Education
Experience
Skills

##Company Profile Module

Allows recruiters to create and manage company information associated with their account.

##Job Management Module

Recruiters can:

Create job postings
Manage existing jobs
View posted jobs
Delete job postings

Job seekers can:
Search jobs
View job opportunities
Apply for suitable positions


## Application Management Module
Provides functionality for:
Applying for jobs
Viewing applicants
Viewing candidate information
Tracking applications
Updating application status.

##Testing
The major application workflows can be tested using the following scenarios.
Authentication
Register a new user
Login with valid credentials
Test invalid login
Logout
Verify session handling
Job Seeker
Create candidate profile
Add education
Add experience
Add skills
Search jobs
View job details
Apply for a job
View submitted applications
Track application status
Recruiter
Create company profile
Post a job
View posted jobs
Manage jobs
Delete a job
View applicants
View candidate profile
Update application status


## Security Considerations
For production-level deployment, the following security enhancements should be implemented:
Password hashing
Strong input validation
SQL injection prevention
CSRF protection
Secure session management
Proper authentication and authorization
Environment-based database credentials
Secure error handling
File upload validation
Access control for recruiter and candidate resources


##Future Enhancements
The project is being developed toward a more complete, real-world job portal platform.
Planned enhancements include:
 Advanced job search
 Job filtering by location, experience, salary, and skills
 Resume upload and management
 Resume download for recruiters
 Saved jobs
 Job recommendations
 Email notifications
 Application status notifications
 Password reset
 Profile completion percentage
 Pagination
 Recruiter analytics dashboard
 Admin dashboard
 User management
 Job moderation
 REST API integration
 Improved responsive UI/UX
 Automated unit testing
 Integration testing
 CI/CD pipeline
 Cloud deployment

 
## Project Objectives
The main objectives of this project are:
Build a real-world Java web application
Understand MVC architecture
Implement database-driven web functionality
Work with Struts 2 framework
Implement DAO-based database operations
Develop role-based application workflows
Practice JSP-based web development
Work with PostgreSQL and JDBC
Understand Maven project management
Deploy Java web applications using Apache Tomcat
Practice Git and GitHub version control


## Concepts Demonstrated
This project demonstrates practical implementation of:
Java Web Development
MVC Architecture
Struts 2 Actions
JSP
JDBC
DAO Pattern
Object-Oriented Programming
CRUD Operations
PostgreSQL Database Integration
Session Management
Role-Based Workflows
Maven
Apache Tomcat
Git & GitHub


##Author
Hariprasath R

Java Developer | Web Developer | SQL

Interested in building real-world applications, backend development, web technologies, and solving practical software engineering problems.

Connect With Me
GitHub: https://github.com/Hariprasath2004
LinkedIn: https://www.linkedin.com/in/hariprasath-r-417487292/
