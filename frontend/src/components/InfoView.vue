<template>

    <v-data-table
        :headers="headers"
        :items="info"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'InfoView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "status", value: "status" },
            ],
            info : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/infos'))

            temp.data._embedded.infos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.info = temp.data._embedded.infos;
        },
        methods: {
        }
    }
</script>

