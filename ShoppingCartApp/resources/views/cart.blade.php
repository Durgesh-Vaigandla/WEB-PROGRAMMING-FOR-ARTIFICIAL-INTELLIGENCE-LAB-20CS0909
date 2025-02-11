<!-- cart.php -->
<?php
session_start();

// Update quantity
if (isset($_POST['update_quantity'])) {
    $product_id = $_POST['product_id'];
    $quantity = max(1, (int)$_POST['quantity']);
    if (isset($_SESSION['cart'][$product_id])) {
        $_SESSION['cart'][$product_id]['quantity'] = $quantity;
    }
}

// Remove item
if (isset($_POST['remove_item'])) {
    $product_id = $_POST['product_id'];
    if (isset($_SESSION['cart'][$product_id])) {
        unset($_SESSION['cart'][$product_id]);
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <style>
        .cart-table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        .cart-table th, .cart-table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        .cart-total {
            text-align: right;
            margin: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Shopping Cart</h1>
    <a href="products.php">Continue Shopping</a>
    
    <?php if (empty($_SESSION['cart'])): ?>
        <p>Your cart is empty.</p>
    <?php else: ?>
        <table class="cart-table">
            <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Subtotal</th>
                <th>Actions</th>
            </tr>
            <?php 
            $total = 0;
            foreach ($_SESSION['cart'] as $product_id => $item): 
                $subtotal = $item['price'] * $item['quantity'];
                $total += $subtotal;
            ?>
                <tr>
                    <td><?php echo $item['name']; ?></td>
                    <td>$<?php echo number_format($item['price'], 2); ?></td>
                    <td>
                        <form method="post" style="display: inline;">
                            <input type="hidden" name="product_id" value="<?php echo $product_id; ?>">
                            <input type="number" name="quantity" value="<?php echo $item['quantity']; ?>" min="1" style="width: 60px;">
                            <button type="submit" name="update_quantity">Update</button>
                        </form>
                    </td>
                    <td>$<?php echo number_format($subtotal, 2); ?></td>
                    <td>
                        <form method="post" style="display: inline;">
                            <input type="hidden" name="product_id" value="<?php echo $product_id; ?>">
                            <button type="submit" name="remove_item">Remove</button>
                        </form>
                    </td>
                </tr>
            <?php endforeach; ?>
        </table>
        <div class="cart-total">
            Total: $<?php echo number_format($total, 2); ?>
        </div>
    <?php endif; ?>
</body>
</html>