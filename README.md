# Securing REST APIs with Spring Security + JWTs

**from an article by DanVega: https://www.danvega.dev/blog/spring-security-jwt**

This is a demo for showing how we can use spring security API to set up user authentication and authorization to access a resource.
We are using server-signed JWT token handled by an authentication service which can provide JWT for new login and verify a rolling
JWT token when a client wants to access a resource.

### What I've learn:
- Setting up SpringSecurity for authentication with OAuth2ResourceServer configured
- Setting up JwtEncoder and JwtDecoder using Nimbus-Jose library
- Setting up a token service which generate token when accessing */token* endpoint.

## How to configure Spring Security for Authentication and Authorization
In a most simplest way, spring security for web can be configured in a SecurityConfig class annotated with @Configuration & @EnableWebSecurity. 

Spring will use a SecurityFilterChain bean to enable security mechanism. In this example, we disabled csrf for
simplicity, because we don't need it yet, and we enabled authorizeRequest to make Spring do authorization for every request made.

After we enabled authorizeRequest, we also need to enable sessionManagement. In this example, the authentication
is stateless which means there is no need to create and maintain HttpSession. Each authenticated client is saved by Spring's SecurityContectHolder, that's why we can use getAuthority() from Authentication class.

## Spring's Basic Authentication Mechanism
Steps when unauthenticated:
1. A client makes unauthenticated request to any endpoint or resource.
2. Spring security have AuthorizationFilter to deny the request by throwing AccessDeniedException.
3. Spring security will handle the exception with ExceptiontranslationFilter to initiate *Start Authentication*. Spring will send WWW-Authenticate header by AuthenticationEntryPoint.

Steps when authenticated:
1. A client sends username and password wrapped in a Authentication header.
2. BasicAuthenticationFilter creates UsernamePasswordAuthenticationToken by extracting username and password from HttpServletRequest.
3. the authentication token is passed into AuthenticationManager to be authenticated.
4. If authenticated, the authentication token saved by SecurityContextHolder.
