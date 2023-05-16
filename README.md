# Microservice Assignment

- Module: ITS 4243 Microservices and Cloud Computing
- Name: Dissanayake D.A.N.P.
- Index: ICT/18/813
- Assignment 2

## Notes
- Circuit breaker was implemented using Resilience4j in the admin service. The following configurations were used for that.

```java
# Circuit Breaker Configurations
resilience4j.circuitbreaker.instances.myCircuitBreaker.registerHealthIndicator=true
resilience4j.circuitbreaker.instances.myCircuitBreaker.slidingWindowSize=100
resilience4j.circuitbreaker.instances.myCircuitBreaker.permittedNumberOfCallsInHalfOpenState=10
resilience4j.circuitbreaker.instances.myCircuitBreaker.waitDurationInOpenState=10000
resilience4j.circuitbreaker.instances.myCircuitBreaker.failureRateThreshold=50
resilience4j.circuitbreaker.instances.myCircuitBreaker.eventConsumerBufferSize=10
resilience4j.circuitbreaker.instances.myCircuitBreaker.recordExceptions=java.io.IOException, java.util.concurrent.TimeoutException, java.lang.RuntimeException
# TimeLimiter Configurations
resilience4j.timelimiter.instances.myTimeLimiter.timeoutDuration=2000
resilience4j.timelimiter.instances.myTimeLimiter.cancelRunningFuture=true

# Retry Configurations
resilience4j.retry.instances.myRetry.maxRetryAttempts=3
resilience4j.retry.instances.myRetry.waitDuration=1000
resilience4j.retry.instances.myRetry.enableExponentialBackoff=true
resilience4j.retry.instances.myRetry.exponentialBackoffMultiplier=2
resilience4j.retry.instances.myRetry.retryExceptions=java.io.IOException
resilience4j.retry.instances.myRetry.ignoreExceptions=java.lang.NullPointerException

```

