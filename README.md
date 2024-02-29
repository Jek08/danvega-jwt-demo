# Securing REST APIs with Spring Security + JWTs

**from an article by DanVega: https://www.danvega.dev/blog/spring-security-jwt**

This is a demo for showing how we can use spring security API to set up user authentication and authorization to access a resource.
We are using server-signed JWT token handled by an authentication server which can provide JWT for new login and verify a rolling
JWT token when client app wants to access a resource.