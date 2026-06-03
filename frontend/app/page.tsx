"use client";

import { useEffect, useState } from "react";

export default function Home() {

  const [products, setProducts] = useState([]);

  const [name, setName] = useState("");

  const [description, setDescription] = useState("");

  const [price, setPrice] = useState("");

  async function fetchProducts() {

    const response = await fetch(
      "http://localhost:8080/products"
    );

    const data = await response.json();

    setProducts(data);
  }

  useEffect(() => {
    fetchProducts();
  }, []);

  async function createProduct() {

    await fetch("http://localhost:8080/products", {

      method: "POST",

      headers: {
        "Content-Type": "application/json",
      },

      body: JSON.stringify({
        name,
        description,
        price: Number(price),
      }),
    });

    setName("");
    setDescription("");
    setPrice("");

    fetchProducts();
  }

  return (

    <main className="p-10 max-w-2xl mx-auto">

      <h1 className="text-4xl font-bold mb-8">
        Manualidades  Movala
      </h1>

      <div className="space-y-4 mb-10">

        <input
          type="text"
          placeholder="Nombre"
          value={name}
          onChange={(e) => setName(e.target.value)}
          className="border p-2 w-full"
        />

        <input
          type="text"
          placeholder="Descripción"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          className="border p-2 w-full"
        />

        <input
          type="number"
          placeholder="Precio"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          className="border p-2 w-full"
        />

        <button
          onClick={createProduct}
          className="bg-black text-white px-4 py-2 rounded"
        >
          Crear producto
        </button>

      </div>

      <div className="space-y-4">

        {products.map((product: any) => (

          <div
            key={product.id}
            className="border p-4 rounded"
          >

            <h2 className="text-2xl font-semibold">
              {product.name}
            </h2>

            <p>{product.description}</p>

            <p className="font-bold mt-2">
              {product.price} €
            </p>

          </div>
        ))}

      </div>

    </main>
  );
}