# Keycloak & Spring Boot 2 OAuth2 client

Sample project to test authentication using [Keycloak](http://www.keycloak.org) with 
[Spring Boot 2.0 OAuth2 Client](https://docs.spring.io/spring-boot/docs/2.0.0.RELEASE/reference/htmlsingle/#boot-features-security-oauth2).

See also [Spring Security: Mapping User Authorities](https://docs.spring.io/spring-security/site/docs/5.0.3.RELEASE/reference/htmlsingle/#oauth2login-advanced-map-authorities)


Works fine except that user always get `ROLE_USER` authority instead of the one defined in keycloak :-(

## Run application

### Keycloak

1. Import realm config `realm-export.json` to Keycloak
1. Edit `spring-boot-test` client to add a new `admin` role
1. Create a user with `admin` role within `spring-boot-test` client

### Application

1. Update `src/main/resources/application.yml` with keycloak url  
1. Run application: `./mvnw spring-boot:run`
1. Go to [http://localhost:8080](http://localhost:8080) and login
1. **User will have `ROLE_USER` authority instead of `admin` :-(**

