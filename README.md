# spring-microservice-architecture
:bowtie: Microservice Architecture with Netflix Eureka, Netflix Zuul ApiGateway and Config Server

```JS
graph TD
	A[zuul-gateway-server] -->|1. Login and get JWT| B(oauth-service)
	B --> C{Login}
  C --> D(user-service)
  D --> |Response|C
  A --> |2. Send JWT to consume| E(item-service)
  E --> F(product-service)
```
[![MERMAID](https://mermaid.ink/img/eyJjb2RlIjoiZ3JhcGggVERcblx0QVt6dXVsLWdhdGV3YXktc2VydmVyXSAtLT58MS4gTG9naW4gYW5kIGdldCBKV1R8IEIob2F1dGgtc2VydmljZSlcblx0QiAtLT4gQ3tMb2dpbn1cbiAgQyAtLT4gRCh1c2VyLXNlcnZpY2UpXG4gIEQgLS0-IHxSZXNwb25zZXxDXG4gIEEgLS0-IHwyLiBTZW5kIEpXVCB0byBjb25zdW1lfCBFKGl0ZW0tc2VydmljZSlcbiAgRSAtLT4gRihwcm9kdWN0LXNlcnZpY2UpXG5cblxuXG4gICIsIm1lcm1haWQiOnsidGhlbWUiOiJkZWZhdWx0In0sInVwZGF0ZUVkaXRvciI6ZmFsc2V9)](https://mermaid-js.github.io/mermaid-live-editor/#/edit/eyJjb2RlIjoiZ3JhcGggVERcblx0QVt6dXVsLWdhdGV3YXktc2VydmVyXSAtLT58MS4gTG9naW4gYW5kIGdldCBKV1R8IEIob2F1dGgtc2VydmljZSlcblx0QiAtLT4gQ3tMb2dpbn1cbiAgQyAtLT4gRCh1c2VyLXNlcnZpY2UpXG4gIEQgLS0-IHxSZXNwb25zZXxDXG4gIEEgLS0-IHwyLiBTZW5kIEpXVCB0byBjb25zdW1lfCBFKGl0ZW0tc2VydmljZSlcbiAgRSAtLT4gRihwcm9kdWN0LXNlcnZpY2UpXG5cblxuXG4gICIsIm1lcm1haWQiOnsidGhlbWUiOiJkZWZhdWx0In0sInVwZGF0ZUVkaXRvciI6ZmFsc2V9)
