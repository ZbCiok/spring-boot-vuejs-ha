<template>
  <div class="LayoutDefault">
    <header>
      <router-link to="/">Home</router-link>&nbsp;&nbsp;
      <router-link to="/product-list">Object List</router-link>&nbsp;&nbsp;
      <router-link to="/product-create">Object Create</router-link>&nbsp;&nbsp;
      <router-link to="/about">
        <span class="red">About</span>
      </router-link>&nbsp;&nbsp;
      <router-link to="/login">Login</router-link>&nbsp;&nbsp;
    </header>

    <main class="LayoutDefault__main">
      <slot />
    </main>

    <footer
      class="LayoutDefault__footer"
    >VueJS example: military-equipment version 0.3; author Zbigiew Ciok, PL</footer>
  </div>
</template>

<script>
export default {
  name: `LayoutDefault`,
  data() {
    return {
      user: null
    };
  }
};
</script>

<style lang="scss">
.LayoutDefault {
  max-width: 78%;
  margin-right: auto;
  margin-left: auto;
  padding-right: 1em;
  padding-left: 1em;

  &__nav {
    padding-top: 1em;
    padding-bottom: 1em;
    border-bottom: 1px solid #c0c0c0;

    background-color: rgba(255, 145, 0, 0.233);
  }

  &__main {
    padding-top: 1.5em;
    padding-bottom: 2em;
  }

  &__footer {
    width: 80%;
    font-size: 12px;
    text-align: center;

    background: rgba(189, 183, 108, 0.99);
    border: 1px solid #000000;

    margin-bottom: 50px;
  }
}

.left-menu {
  float: left;
  margin-top: -27px;
  margin-left: 20px;
}
.right-menu {
  float: right;
  margin-top: -27px;
  margin-right: 20px;
}
.red {
  color: red;
}

header {
  width: 1162px;
  max-width: 100%;
  padding: 10px;

  position: fixed;
  z-index: 999;
  background: rgba(189, 183, 108, 0.99);
  border: 1px solid #000000;
  box-shadow: 0 10px 10px rgba(50, 50, 50, 0.95);
}
</style>

