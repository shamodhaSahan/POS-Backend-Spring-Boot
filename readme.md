
# Spring Boot Application

This is a restful api for pos system

<div align="center">
	<code><img width="30" src="https://user-images.githubusercontent.com/25181517/192107858-fe19f043-c502-4009-8c47-476fc89718ad.png" alt="REST" title="REST"/></code>
	<code><img width="30" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" alt="InteliJ" title="InteliJ"/></code>
	<code><img width="30" src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" alt="Spring Boot" title="Spring Boot"/></code>
	<code><img width="30" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/></code>
	<code><img width="30" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" alt="Maven" title="Maven"/></code>
	<code><img width="30" src="https://user-images.githubusercontent.com/25181517/117207493-49665200-adf4-11eb-808e-a9c0fcc2a0a0.png" alt="Hibernate" title="Hibernate"/></code>
	<code><img width="30" src="https://user-images.githubusercontent.com/25181517/117533873-484d4480-afef-11eb-9fad-67c8605e3592.png" alt="JUnit" title="JUnit"/></code>
	<code><img width="30" src="https://user-images.githubusercontent.com/25181517/183896128-ec99105a-ec1a-4d85-b08b-1aa1620b2046.png" alt="MySQL" title="MySQL"/></code>
</div>

## Customer API Reference

#### Get all customers

```http
  GET api/v1/customer
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **UUID ** auto generated |
| `nic` | `string` | **No duplicates**. |
| `name` | `string` | **No numbers, No symbol**. |
| `salary` | `double` | **Valid Price Only**. |
| `address` | `string` | **Valid Price Only**. |

#### Get customer

```http
  GET api/v1/customer/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of customer to fetch |

#### Post customer

```http
  POST api/v1/customer
```

| Body |      Content-Type     | Description        |
| :-------- | :-------------- | :-------------------|
| `customer`      | `application/json`| **Only use json object**.|

#### Update customer

```http
  PATCH api/v1/customer/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of customer to update |

| Body |      Content-Type     | Description        |
| :-------- | :-------------- | :-------------------|
| `customer`      | `application/json`| **Only use json object**.|

#### Delete customer

```http
  DELETE api/v1/customer/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of customer to delete |




## Item API Reference

#### Get all item

```http
  GET api/v1/item
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `code` | `string` | **UUID ** auto generated |
| `description` | `string` | **** |
| `qtyOnHand` | `int` | **Integer values Only**. |
| `unitPrice` | `double` | **Valid Price Only**. |

#### Get item

```http
  GET api/v1/item/${code}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `code`      | `string` | **Required**. Id of item to fetch |

#### Post item

```http
  POST api/v1/item
```

| Body |      Content-Type     | Description        |
| :-------- | :-------------- | :-------------------|
| `item`      | `application/json`| **Only use json object**.|

#### Update item

```http
  PATCH api/v1/item/${code}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `code`      | `string` | **Required**. Id of item to update |

| Body |      Content-Type     | Description        |
| :-------- | :-------------- | :-------------------|
| `item`      | `application/json`| **Only use json object**.|

#### Delete item

```http
  DELETE api/v1/item/${code}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `code`      | `string` | **Required**. Id of item to delete |




## Order API Reference

#### Get all order

```http
  GET api/v1/order
```
order
| Parameter | Type     | Example                |
| :-------- | :------- | :------------------------- |
| `orderId` | `string` | **O001** |
| `date` | `LocalDate` | **2023-01-01** |
| `customerId` | `string` | ****valid customer's id Only. |
| `orderDetails` | `list of json` | **order Details object list**. |

orderDetails
| Parameter | Type     | Example                |
| :-------- | :------- | :------------------------- |
| `orderId` | `string` | **O001** |
| `itemCode` | `string` | ****valid item's code only. |
| `qty` | `int` | **Integer values Only**. |
| `unitPrice` | `double` | **v Price Only**. |

#### Get order

```http
  GET api/v1/order/${orderId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `orderId`      | `string` | **Required**. Id of order to fetch |

#### Post order

```http
  POST api/v1/order
```

| Body |      Content-Type     | Description        |
| :-------- | :-------------- | :-------------------|
| `order`      | `application/json`| **Only use json object**.|

#### Update order

```http
  PATCH api/v1/order/${orderId}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `orderId`      | `string` | **Required**. Id of order to update |

| Body |      Content-Type     | Description        |
| :-------- | :-------------- | :-------------------|
| `order`      | `application/json`| **Only use json object**.|

#### Delete order

```http
  DELETE api/v1/order/${orderId}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `orderId`      | `string` | **Required**. Id of order to delete |



