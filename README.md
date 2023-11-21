# Promotion

## 목차
- [프로젝트 소개](#프로젝트-소개)
- [실행 방법](#실행-방법)
- [ERD](#ERD)
- [API 문서](#API-문서)

## 프로젝트 소개
Promotion 프로젝트는 회원들에게 매일 한 번씩 '추첨하기' 기회를 제공하는 이벤트 중심의 애플리케이션입니다. 이 기능을 통해 사용자는 1등, 2등, 3등, 또는 꽝 중 하나의 결과를 얻게 됩니다.
상품의 재고를 정확하게 관리하여 이벤트의 공정성과 정확성을 보장하기 위해, '추첨하기' 기능에 분산락을 적용했습니다. 이 분산락은 Redis의 Redisson을 이용하여 구현되었으며, AOP와 어노테이션을 활용하여 코드 중복을 최소화하는 방식으로 설계되었습니다.

## 실행 방법
```bash
[ Step 1 ]

# redis server run 
docker-compose up -d
```
```bash
[ Step 2 ]

# SMTP 설정
# application.yml

mail:
  host: smtp.gmail.com
  port: 587
  username: {Google 계정명}
  password: {Google 앱 비밀번호}
  properties:
    mail.smtp.auth: true
    mail.smtp.starttls.enable: ture
```
```bash
[ Step 3 ]

# Spring Boot Application Run
```

## ERD
<img width="400" alt="image" src="https://github.com/topyheun/promotion/assets/41532299/5fdc1b98-442c-4cf1-88b0-26a1f9a19443"><br>
[[ ERD Docs ]](https://dbdocs.io/gmsdl1994/topy_promotion)

## API 문서
API의 상세한 개요, 사용 예제, 파라미터 설명, 응답 형식 등을 포함한 자세한 문서는 GitBook에 호스팅되어 있습니다.<br>
[[ API Docs ]](https://topys-organization.gitbook.io/topys-promotion/)