import axios from 'axios' import VueAxios from 'vue-axios'

<template>
  <div class="container">
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input
          type="file"
          id="file"
          ref="file"
          v-on:change="handleFileUpload()"
        />
      </label>
      <button v-on:click="submitFile()">Submit</button>
    </div>
  </div>
</template>

<script>

export default {
  name: "FileUploadComponent",

  data() {
    return {
      file: "",
    };
  },

  methods: {

    submitFile() {

      let formData = new FormData();

      formData.append("file", this.file);


    },

    /*
      Handles a change on the file upload
    */
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    },
  },
};
</script>
