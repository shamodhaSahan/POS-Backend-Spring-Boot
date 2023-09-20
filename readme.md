
# Spring Boot Application

This is a restful api for pos system

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



