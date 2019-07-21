<template>

  <layout name="LayoutDefault">
    <div>
      <br/><br/>
      <h2 class="list">Object List</h2>
      <br />
      <form @submit="formSubmit">
        <span class="select-category">Select category:&nbsp;</span>
        <select v-model="selected">
          <option
            v-for="(option, i) in options"
            v-bind:value="option.value"
            v-bind:key="i"
          >{{ option.text }}</option>
        </select>
        &nbsp;
        <button>Submit</button>
      </form>
      <br />

      <table class="list">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Country</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(product, index) in products" v-bind:key="index">
            <td width="4%">{{ product.id }}</td>
            <td width="10%">{{ product.name }}</td>
            <td>{{ product.description }}</td>
            <td>{{ product.country }}</td>
            <td width="280px">
              <div class="action-center">
                <div class="action">
                  <router-link
                    :to="{ name: 'product-show', params: { id: index } }"
                    tag="button"
                  >Show</router-link>
                </div>
                <div class="action">
                  <router-link
                    :to="{ name: 'product-edit', params: { index: index } }"
                    tag="button"
                  >Edit</router-link>
                </div>
                <div class="action">
                  <router-link
                    :to="{ name: 'product-hard-delete', params: { index: index } }"
                    tag="button"
                  >Hard&nbsp;Delete</router-link>
                </div>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </layout>
</template>

<script>
import ProductService from "@/services/ProductService.js";
import Layout from "@/layouts/Layout";

export default {
  name: `Home`,
  components: {
    Layout
  },

  data() {
    return {
      selected: "0",

      options: [
        { text: "Tanks", value: "0" },
        { text: "Bombers", value: "1" },
        { text: "Warships", value: "2" }
      ],
      products: []
    };
  },

  created() {
    this.selected = this.$session.get("selected");
    if (this.selected === "0") {
      this.getCategory("/category/tank");
    } else if (this.selected === "1") {
      this.getCategory("/category/bomber");
    } else if (this.selected === "2") {
      this.getCategory("/category/warship");
    }
  },

  methods: {
    formSubmit(e) {
      e.preventDefault();
      if (this.selected === "0") {
        this.getCategory("/category/tank");
      } else if (this.selected === "1") {
        this.getCategory("/category/bomber");
      } else if (this.selected === "2") {
        this.getCategory("/category/warship");
      }
      this.$session.set("selected", this.selected);
    },

    getCategory(url) {
      ProductService.getProducts(url)
        .then(response => {
          this.products = response.data;
        })
        .catch(error => {
          console.log("ProductList: There was an error:", error.response);
        });
    }
  }
};
</script>

<style>
span.select-category {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
  font-size: 16px;
}
h2.list {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}

/* table.list body {
  
} */
table.list {
  font-size: 14px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 80%;
}
table.list th {
  padding: 0.25rem;
  text-align: center;
  border: 1px solid #ccc;
}
table.list td {
  padding: 0.25rem;
  text-align: left;
  border: 1px solid #ccc;
}
table.list thead tr {
  background: #ddd;
}
table.list tbody tr:hover {
  background: #e0daa4;
}

div.action-center {
  text-align: center;
}

div.action {
  display: inline;
  padding-right: 1px;
}
</style>