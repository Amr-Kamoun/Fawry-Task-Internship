
# 🛒 Fawry Task Internship — Shopping Cart System

A simple Java-based shopping cart system simulating a checkout process for shippable and digital products.  
The implementation supports validation, shipping weight calculation, and customer balance deduction.

## 📁 Project Structure

```
.
├── app/
│   └── Main.java
├── model/
│   ├── CartItem.java
│   ├── Customer.java
│   ├── Product.java
│   └── Shippable.java
├── product/
│   ├── DigitalProduct.java
│   ├── ShippableExpirableProduct.java
│   └── ShippableProduct.java
├── service/
│   ├── Cart.java
│   ├── CheckoutService.java
│   └── ShippingService.java
└── README.md
```

## 🚀 How to Run

1️⃣ Compile all sources:
```bash
javac -d . model/*.java product/*.java service/*.java app/*.java
```

2️⃣ Run the program:
```bash
java -cp . app.Main
```

## 📋 Example Output

```
INFO: ** Shipment notice **
INFO: Cheese    200g
INFO: Biscuits  700g
INFO: Total package weight 0.9kg

INFO: ** Checkout receipt **
INFO: 2x Cheese       200
INFO: 1x Biscuits     150
----------------------
INFO: Subtotal         350
INFO: Shipping         30
INFO: Amount           380
INFO: Remaining balance 620
```

## 🔷 Corner Cases Covered

✅ **Empty Cart:** Throws error if no items in cart.  
✅ **Expired Product:** Throws error if any item is expired.  
✅ **Out of Stock:** Throws error if quantity requested > available stock.  
✅ **Insufficient Balance:** Throws error if customer balance < total amount.  

## 🔷 Assumptions

- Products implementing `Shippable` contribute to shipping weight and cost.
- Flat shipping fee: `10` per unit of shippable product.
- Prices are in integer currency units.
- Customer starts with a sufficient balance (`1000` in the example).
- Digital products (e.g., ScratchCard) do not require shipping.

