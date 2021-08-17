# PV_APP
> Photovoltaic calculator which supports offer process. This
application allows the user to predesign photovoltaic
installation and calculate a price, production, and technical
parameters by using question form or choose components
manually. Github [_link_](https://github.com/TomaszSulkowski94/PV_App). 

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Contact](#contact)


## General Information
This project was inspired my work as a project coordinator at photovoltaic company.
The PV_APP support designing and offering photovoltaic installation process. 
Based on the simple question form, customer provide an answers for the questions (electricity bills, roof angle, roof postition, roof type, roof material) to pre-design photovoltaic installation and show it as a simple report with an offer.
Application resolve problem with collecting information and sharing them between employees (customer => sales advisor => electrical designer). 
After creating electrical designer receives basic technical parameters of the installation and components.

## Technologies Used
- Java 
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- HQL
- H2 Database
- JUNIT 4

## Features
List the ready features here:
- Pre-designing installation based on the question form (as a customer)
- Designing installation manually (as a photovoltaic designer)
- Preparing offer
- Recalculating technical outputs
- Allow designer/ sales advisor to see user question form
- Allow designer to set a discount (0-40%)
- Import components from excel file to application
- Export the import template
- Export list of the components from database to pdf file
- CRUD operations for components (PV modules, inverters, construction) and for designed installation
- Creating a simple report with an offer after designing process

<!-- TO BE ADDED after adding GUI -->
<!--## Screenshots-->
<!--[Example screenshot](./img/screenshot.png)-->

## Usage
- Question form page: http://localhost:8080/questionform/create
- Instalation desgining page: http://localhost:8080/instalation/design
- Instalation managing page: http://localhost:8080/instalation/list
- Components managing pages
  - Photovoltaic modules: http://localhost:8080/modules/modulelist
  - Inverters: http://localhost:8080/inverter/list
  - Constructions: http://localhost:8080/construction/list

>User case 1:
> 
> You are a customer and you are looking for an offer on photovoltaic
> installation. You need to open the question form page http://localhost:8080/questionform/create
> after answering for listed questions application redirect the user to the report page.
> 
>Question list:
>- How big is your monthly electricity bill?
>- Where the installation will be located?
>- What kind of material roof is made?
>- How big is the angle of your roof?
>- Which roof direction do you have?

>User case 2:
>
> You are a sales advisor. Your customer-specified requirements for PV installation during the business meeting
> you need to prepare for him an offer. You are opening the design page http://localhost:8080/instalation/design
> after specifying components and setting roof parameters. You can show your customer an offer.
> At the next meeting customer is interested in your offer, but he wants a 10% discount. During negotiation
> both sides agreed to a 7% discount. You need to open the installation managing page
> http://localhost:8080/instalation/list to set a discount please click on the percentage icon (%) near your installation.
> At form at the next page specify 7 value. After signing a contract you can send installation id to electric designer
> with basic technical calculations.
 

>User case 3:
>
> You are working at the photovoltaic company. Today you have a company meeting about sales strategy for next few weeks.
> You need to take all available components for this meeting. You need to open the components page and download components list
> from the database system. After a meeting, your boss selected some components and changed their prices. To perform this action
> you need to open components pages and click on the pencil icon to change the price.
 

## Project Status
Project is: in progress.

## Room for Improvement
Room for improvement:
- Refactor code

To do:
- Use spring security to create designer, sales account and add permissions
- Create a simple GUI based on the bootstrap and CSS
- Improve final report
- Add send offers via email feature
- Create customers DB to store potential customers
- Adding offer status

## Contact
Created by [@Tomasz Sułkowski](https://www.linkedin.com/in/tomasz-su%C5%82kowski/) - 
feel free to contact me!

