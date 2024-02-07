# Queries
___
### Find all employees
```
query {
	findAll {
    id name age email phone
  }
}
```
___
### Find employee by id
```
query {
  findById(id: "e0a8b6db-fdba-4546-98ce-e3cc973120f9") {
    id name age email phone
  }
}
```
___
### Insert new employee
```
mutation {
  insert(name: "João", age: 25, email: "joao@gmail.com", phone: "11987654321") {
    id name age email phone
  }
}
```
___
### Update employee
```
mutation {
  update(id: "e0a8b6db-fdba-4546-98ce-e3cc973120f9", name: "Pedrovisk" age:32, email: "pedrovisk@gmail.com", phone: "11999999999") {
    id name age email phone
  }
}
```
___
### Delete employee
```
mutation {
  delete(id: "e0a8b6db-fdba-4546-98ce-e3cc973120f9")
}
```

___

## Executing Docker compose and accessing Redis bash:
- `docker compose up -d`
- `docker exec -it cache-redis sh`

## Redis CLI:
- `redis-cli`
- `keys * = view keys created`
- `get [key]::[value] = get serializable content. ex: get employees::e0a8b6db-fdba-4546-98ce-e3cc973120f9`
