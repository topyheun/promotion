## Introduction
**(Topy's Promotion)** is a project that implements a promotion domain using <ins>Redis(Redisson)</ins> library to control <ins>Concurrency Issues</ins>. <ins>Distributed lock</ins> was implemented using <ins>Spring AOP</ins> and <ins>Custom Annotation</ins>, and the promotion status was managed using <ins>Scheduler</ins>.<r>
The main function is the process of presenting prizes through a lottery when participating in a promotion.

The main purpose of this project is to control concurrency issues. Therefore, only the sign-up functionality has been implemented in a simplified way for user domains.
<br><br>

## Stack
Spring Boot 3.1<br>
MariaDB, JPA<br>
Redis(Redisson)<br>
Junit5, Mokito<br>
AWS
<br><br>

## ERD
<img width="1052" alt="image" src="https://github.com/topyheun/promotion/assets/41532299/001d69e0-19cb-4d81-abee-a84034daa50c"><br>
[[ erd docs ]](https://dbdocs.io/gmsdl1994/topy_promotion)
<br><br>

## API Documentation
<img width="955" alt="image" src="https://github.com/topyheun/promotion/assets/41532299/d2b7d55e-cfe5-4712-bbc0-d2347a36b158"><br>
<br><br>

## API Testing
TODO
<br><br>