# Spring Boot

## Differences between @Component, @Repository, @Controller, @Entity and @Service

<context:component-scan> only scans @Component and does not look for @Controller, 
@Service and @Repository in general. They are scanned because they themselves are 
annotated with @Component.

Example : 
```
@Component
public @interface Service {
    ….
}
```
@Controller, @Entity, @Service and @Repository are special types of @Component annotation

[Reference](https://stackoverflow.com/questions/6827752/whats-the-difference-between-component-repository-service-annotations-in)

<hr />

## What’s special about @Controller?

@Controller annotation indicates that a particular class serves the role of a controller. 
The @Controller annotation acts as a stereotype for the annotated class, indicating its role

dispatcher scans the classes annotated with @Controller and detects methods annotated with 
@RequestMapping annotations within them. We can use @RequestMapping on/in only those methods 
whose classes are annotated with @Controller and it will NOT work with @Component, @Service, @Repository etc...

<hr />

## What’s special about @Service?

@Service beans hold the business logic and call methods in the repository layer.
There’s nothing else noticeable in this annotation.

<hr />

## General Purpose of @Component Annotation?

@Component  are used to auto-detect and auto-configure beans using 
classpath scanning. There's an implicit one-to-one mapping between the annotated class and the bean 
(i.e. one bean per class). Control of wiring is quite limited with this approach, it's purely declarative.

<hr />

## @Bean Annotation is used for?

As we know, @Component Preferable for component scanning and automatic wiring.
Sometimes automatic configuration is not an option. For Example : Let's imagine that we want to wire components from 
third-party libraries (we don't have the source code so we can't annotate its classes with @Component), so automatic 
configuration is not possible.
@Bean annotation returns an object that spring should register as bean in application context. The body of the method 
bears the logic responsible for creating the instance.

[Reference](https://stackoverflow.com/questions/10604298/spring-component-versus-bean)

<hr />

## What is a Dispatcher Servlet?

A DispatcherServlet is to take an incoming URI and find the right combination of handlers (generally methods on Controller
 classes) and views (generally JSPs) that combine to form the page or resource that's supposed to be found at that location.

<hr />

## How @Autowiring works?

ComponentScan for classes with the following annotations @Controller, @Service, ....
Bean class should also be defined as bean, using @Service or any other annotations.