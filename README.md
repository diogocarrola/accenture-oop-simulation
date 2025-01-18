# accenture-oop-simulation
Help a client take control of their fast growing code base, as a software engineer at Accenture

## Task 1: Search Functionality
The application includes a search feature that allows users to find products by name or description. The search is implemented as an HTTP GET endpoint at `/api/products/search`, which accepts a query parameter. The search is case-insensitive and supports both partial and exact matches when quotes are used.

## Task 2: Refactor Search Functionality
In this task, the search logic was refactored from the `SearchController` into a new `SearchService` class. This service is now used by both the `SearchController` and the `ReportController`. The refactoring ensures that the search functionality is reusable and adheres to the principles of service-oriented architecture in Spring.