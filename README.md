## Introduction
**(Topy's Promotion)** is a project that implements a promotion domain using <ins>Redis(Redisson)</ins> library to control <ins>Concurrency Issues</ins>. <ins>Distributed lock</ins> was implemented using <ins>Spring AOP</ins> and <ins>Custom Annotation</ins>, and the promotion status was managed using <ins>Scheduler</ins>.<r>
The main function is the process of presenting prizes through a lottery when participating in a promotion.

The main purpose of this project is to control concurrency issues. Therefore, only the sign-up functionality has been implemented in a simplified way for user domains.
<br><br>

## Stack
Spring Boot 3.1<br>
MariaDB, JPA, Querydsl<br>
Redis(Redisson)<br>
Junit5, Mokito<br>
AWS, Docker
<br><br>

## ERD
<img width="1106" alt="image" src="https://github.com/topyheun/promotion/assets/41532299/5fdc1b98-442c-4cf1-88b0-26a1f9a19443"><br>
[[ ERD Docs ]](https://dbdocs.io/gmsdl1994/topy_promotion)
<br><br>

## API Documentation
To gain a comprehensive understanding of the Topy's Promotion API, please refer to our detailed API Documentation available at [[ API Docs ]](https://topys-organization.gitbook.io/topys-promotion/). It provides information about each endpoint, including request and response structures
<br><br>