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



