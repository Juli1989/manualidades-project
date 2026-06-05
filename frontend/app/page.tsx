"use client";

import { useEffect, useState } from "react";

import { Playfair_Display }
from "next/font/google";

const playfair = Playfair_Display({
  subsets: ["latin"],
  weight: ["400", "600"],
});

export default function Home() {

  
  const [products, setProducts] = useState([]);

  const [name, setName] = useState("");

  const [description, setDescription] = useState("");

  const [price, setPrice] = useState("");
  const [isLogged, setIsLogged] =
    useState(false);

  async function fetchProducts() {

    try {

      const response = await fetch(
        "http://localhost:8080/products"
      );

      console.log(response);

      if (!response.ok) {

        console.log("Error status:", response.status);

        return;
      }

      const data = await response.json();

      setProducts(data);

    } catch (error) {

      console.log(error);
    }
  }

  useEffect(() => {

    fetchProducts();

    const token =
      localStorage.getItem("token");

    setIsLogged(!!token);

  }, []);

  async function createProduct() {

    const token =
      localStorage.getItem("token");

    const response = await fetch(
      "http://localhost:8080/products",
      {

        method: "POST",

        headers: {

          "Content-Type": "application/json",

          "Authorization":
            `Bearer ${token}`,
        },

        body: JSON.stringify({
          name,
          description,
          price: Number(price),
        }),
      }
    );

    console.log(response);

    setName("");

    setDescription("");

    setPrice("");

    fetchProducts();
  }

  return (

  <main className="bg-[#f7f5f2] min-h-screen">

    <nav className="
  flex
  flex-col
  items-center
  py-8
">

  <div className="
    flex
    items-center
    gap-6
  ">

    <img
      src="/movala-logo.png"
      className="
        w-74
        h-74
        object-cover
        rounded-full
      "
    />

    <h1 className={`
  ${playfair.className}
  text-4xl
  tracking-[4px]
`}>

      MOVALA

    </h1>

  </div>

  <div className="
    flex
    gap-8
    text-sm
    mt-8
  ">

    <a href="/">Shop</a>

    <a href="/">About</a>

    <a href="/">Contact</a>

    <a href="/login">
      Login
    </a>

  </div>

</nav>

    <section className="text-center py-10">

      <h2 className="
        text-4xl
        mb-4
        font-light
      ">

        Joyas hechas a mano

      </h2>

      <p className="text-gray-600">

        Hechas con amor.
        Thoughtfully designed.

      </p>

    </section>

    <section className="
      grid
      grid-cols-1
      md:grid-cols-3
      gap-6
      px-6
      mb-16
    ">

      <div>

        <img
          src="https://images.unsplash.com/photo-1617038260897-41a1f14a8ca0"
          className="
            w-full
            h-[500px]
            object-cover
          "
        />

        <button className="
          border
          border-black
          w-full
          py-4
          mt-4
          hover:bg-black
          hover:text-white
          transition
        ">

          Comprar ahora

        </button>

      </div>

      <div>

        <img
          src="https://images.unsplash.com/photo-1535632066927-ab7c9ab60908"
          className="
            w-full
            h-[500px]
            object-cover
          "
        />

        <button className="
          border
          border-black
          w-full
          py-4
          mt-4
          hover:bg-black
          hover:text-white
          transition
        ">

          Comprar ahora

        </button>

      </div>

      <div>

        <img
          src="https://images.unsplash.com/photo-1611652022419-a9419f74343d"
          className="
            w-full
            h-[500px]
            object-cover
          "
        />

        <button className="
          border
          border-black
          w-full
          py-4
          mt-4
          hover:bg-black
          hover:text-white
          transition
        ">

          Comprar ahora

        </button>

      </div>

    </section>

    <section className="
      flex
      justify-center
      gap-10
      mb-16
      text-sm
    ">

      <button>All items</button>

      <button>Earrings</button>

      <button>Necklaces</button>

      <button>Rings</button>

    </section>

    {isLogged && (

      <div className="
        max-w-xl
        mx-auto
        space-y-4
        mb-16
        bg-white
        p-8
        rounded-2xl
      ">

        <input
          type="text"
          placeholder="Nombre"
          value={name}
          onChange={(e) =>
            setName(e.target.value)
          }
          className="
            border
            p-3
            w-full
          "
        />

        <input
          type="text"
          placeholder="Descripción"
          value={description}
          onChange={(e) =>
            setDescription(e.target.value)
          }
          className="
            border
            p-3
            w-full
          "
        />

        <input
          type="number"
          placeholder="Precio"
          value={price}
          onChange={(e) =>
            setPrice(e.target.value)
          }
          className="
            border
            p-3
            w-full
          "
        />

        <button
          onClick={createProduct}
          className="
            bg-black
            text-white
            px-6
            py-3
            w-full
          "
        >

          Crear producto

        </button>

      </div>
    )}

    <section className="
      grid
      grid-cols-1
      md:grid-cols-3
      gap-10
      px-10
      pb-20
    ">

      {products.map((product: any) => (

        <div
          key={product.id}
          className="
            bg-white
            p-4
          "
        >

          <div className="
            h-[350px]
            bg-gray-100
            mb-4
          ">

          </div>

          <h2 className="
            text-xl
            mb-2
          ">

            {product.name}

          </h2>

          <p className="
            text-gray-500
            mb-4
          ">

            {product.description}

          </p>

          <p className="
            font-semibold
          ">

            {product.price} €

          </p>

        </div>
      ))}

    </section>

  </main>
);}