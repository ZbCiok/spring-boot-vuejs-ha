<template>
  <layout name="LayoutDefault">
    <div>
      <br/><br/>
      <h2 class="create">Object Create</h2>
      <br/>
      <form @submit.prevent="formSubmit">
        <table class="create">
          <tr>
            <td>Category:</td>
            <td>
              <select v-model="selected">
                <option
                  v-for="(option, i) in options"
                  v-bind:value="option.value"
                  v-bind:key="i"
                >{{ option.text }}</option>
              </select>
            </td>
          </tr>
          <tr>
            <td>Name:</td>
            <td>
              <div>
                <input v-model="$v.product.name.$model" type="text" class="input-field" />

                <span
                  class="error-invalid-form"
                  v-if="!$v.product.name.required"
                ><br/>Name is required between 3 and 100 characters..</span>

                <span
                  class="error-invalid-form"
                  v-if="!$v.product.name.minLength"
                ><br/>Name must have at least {{ $v.product.category.$params.minLength.min }} characters.</span>

                <span
                  class="error-invalid-form"
                  v-if="!$v.product.name.maxLength"
                ><br/>Name must have a maximum of {{ $v.product.name.$params.maxLength.max }} characters.</span>
              </div>
            </td>
          </tr>
          <tr>
            <td>Description:</td>
            <td>
              <div>
                <textarea v-model="$v.product.description.$model" class="input-field-text" />

                <span
                  class="error-invalid-form"
                  v-if="!$v.product.description.maxLength"
                ><br/>Description must have a maximum of {{ $v.product.description.$params.maxLength.max }} characters.</span>
              </div>
            </td>
          </tr>
          <tr>
            <td>Country:</td>
            <td>
              <div>
                <input v-model="$v.product.country.$model" type="text" class="input-field" />

                <span
                  class="error-invalid-form"
                  v-if="!$v.product.country.maxLength"
                ><br/>Country must have a maximum of {{ $v.product.country.$params.maxLength.max }} characters.</span>
              </div>
            </td>
          </tr>
          <tr>
            <td>Image name:</td>
            <td>
              <div>
                <input v-model="$v.product.image.$model" type="text" class="input-field" />

                <span
                  class="error-invalid-form"
                  v-if="!$v.product.image.maxLength"
                ><br/>Image name must have a maximum of {{ $v.product.image.$params.maxLength.max }} characters.</span>
              </div>
            </td>
          </tr>
        </table>
        <br />
        <button>Create</button>
      </form>
    </div>
  </layout>
</template>

<script>
import { required, minLength, maxLength } from "vuelidate/lib/validators";
import ProductService from "@/services/ProductService.js";
import Layout from "@/layouts/Layout";

export default {
  name: "Product Create",
  components: {
    Layout
  },

  name: "app",
  data() {
    return {
      selected: "tank",

      options: [
        { text: "Tanks", value: "tank" },
        { text: "Bombers", value: "bomber" },
        { text: "Warships", value: "warship" }
      ],

      product: {
        category: "",
        name: "",
        description: "",
        country: "",
        image: ""
      },

      submitted: false
    };
  },
  validations: {
    product: {
      name: { required, minLength: minLength(3), maxLength: maxLength(100) },
      description: { maxLength: maxLength(2000) },
      country: { maxLength: maxLength(50) },
      image: { maxLength: maxLength(250) }
    }
  },
  methods: {
    formSubmit(e) {
      // e.preventDefault();
      this.submitted = true;

      // stop here if form is invalid
      this.$v.$touch();
      if (this.$v.$invalid) {
        this.$swal.fire({
          title: "Fields validation error!",
          animation: false,
          allowOutsideClick: false,
          allowEscapeKey: false,
          allowEnterKey: false,
          backdrop: true
        });
        return;
      }
      this.dialogConfirm();
    },

    dialogConfirm() {
      this.$swal({
        animation: false,
        allowOutsideClick: false,
        allowEscapeKey: false,
        allowEnterKey: false,
        backdrop: true,

        title: "Save data?",
        text: "",
        type: "question",
        showCancelButton: true,
        confirmButtonColor: "#33a5ff ",
        cancelButtonColor: "#33a5ff ",
        confirmButtonText: "Yes",
        cancelButtonText: "No"
      }).then(result => {
        if (result.value) {
          this.product.category = this.selected;
          ProductService.postEvent(this.product)
            .then(response => {
              this.products = response.data;
            })
            .catch(error => {
              console.log("Create: There was an error:", error.response);
            });
          // this.$router.push({ name: "product-list" });
          // window.open("/product-list", "_self");
        }
      });
    }
  }
};
</script>

<style>
table.create {
  border: solid 0px #ddd;
  margin: 0px;
  padding: 0px;
  border-spacing: 0px;
  border-collapse: collapse;
  line-height: 22px;
  font-size: 16px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}

table.create td {
  border: solid 0px #ddd;
  margin-right: 10;
  padding-right: 14px;
}
h2.create {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}
.input-field {
  margin-bottom: 0px;
  margin-top: 15px;
  width: 400px;
  font-size: 16px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}
.input-field-text {
  margin-top: 15px;
  height: 200px;
  width: 414px;
  font-size: 16px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}

.error-invalid-form {
  font-size: 12px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
  color: red;
}
</style>
