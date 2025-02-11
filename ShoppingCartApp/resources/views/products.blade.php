<!-- products.php -->
<?php
session_start();
if (!isset($_SESSION['cart'])) {
    $_SESSION['cart'] = array();
}

// Sample product data (in real application, this would come from a database)
$products = [
    1 => ['name' => 'Product 1', 'price' => 19.99, 'image' => 'product1.jpg'],
    2 => ['name' => 'Product 2', 'price' => 29.99, 'image' => 'product2.jpg'],
    3 => ['name' => 'Product 3', 'price' => 39.99, 'image' => 'product3.jpg']
];

// Add to cart functionality
if (isset($_POST['add_to_cart'])) {
    $product_id = $_POST['product_id'];
    if (isset($_SESSION['cart'][$product_id])) {
        $_SESSION['cart'][$product_id]['quantity']++;
    } else {
        $_SESSION['cart'][$product_id] = [
            'name' => $products[$product_id]['name'],
            'price' => $products[$product_id]['price'],
            'quantity' => 1
        ];
    }
    header("Location: products.php");
    exit();
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
    <style>
        .product-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            padding: 20px;
        }
        .product-card {
            border: 1px solid #ddd;
            padding: 15px;
            text-align: center;
        }
        .cart-link {
            float: right;
            margin: 20px;
        }
    </style>
</head>
<body>
    <a href="cart.php" class="cart-link">View Cart (<?php echo count($_SESSION['cart']); ?>)</a>
    <div class="product-grid">
        <?php foreach ($products as $id => $product): ?>
            <div class="product-card">
                <img src="<?php echo $product['image']; ?>" alt="<?php echo $product['name']; ?>" style="max-width: 200px;">
                <h3><?php echo $product['name']; ?></h3>
                <p>$<?php echo number_format($product['price'], 2); ?></p>
                <form method="post">
                    <input type="hidden" name="product_id" value="<?php echo $id; ?>">
                    <button type="submit" name="add_to_cart">Add to Cart</button>
                </form>
            </div>
        <?php endforeach; ?>
    </div>
</body>
</html>