<script>
import api from './services/api'

export default {
  name: 'App',
  data() {
    return {
      people: [],
      form: { name: '', birthDate: '' },
      editingId: null,
      loading: false,
      error: null,
    }
  },
  async mounted() { await this.load(); },
  methods: {
    async load() {
      this.loading = true; this.error = null;
      try {
        const { data } = await api.get('/people');
        this.people = data;
      } catch (e) {
        this.error = 'Falha ao carregar pessoas';
      } finally { this.loading = false; }
    },
    startEdit(p) {
      this.editingId = p.id;
      this.form = { name: p.name, birthDate: p.birthDate };
    },
    cancelEdit() {
      this.editingId = null;
      this.form = { name: '', birthDate: '' };
    },
    async save() {
      this.loading = true; this.error = null;
      try {
        const payload = { ...this.form };
        if (!this.editingId) {
          await api.post('/people', payload);
        } else {
          await api.put(`/people/${this.editingId}`, payload);
        }
        await this.load();
        this.cancelEdit();
      } catch (e) {
        this.error = e.response?.data?.message || 'Erro ao salvar';
      } finally { this.loading = false; }
    },
    async remove(id) {
      if (!confirm('Excluir esta pessoa?')) return;
      this.loading = true; this.error = null;
      try {
        await api.delete(`/people/${id}`);
        await this.load();
      } catch (e) {
        this.error = 'Erro ao excluir';
      } finally { this.loading = false; }
    }
  }
}
</script>

<template>
  <div style="max-width: 760px; margin: 24px auto; font-family: system-ui, -apple-system, Segoe UI, Roboto, Arial;">
    <h1>Pessoas</h1>

    <section style="border: 1px solid #ddd; padding: 16px; border-radius: 8px; margin-bottom: 16px; background:#fff;">
      <h2 style="margin-top:0">{{ editingId ? 'Editar pessoa' : 'Nova pessoa' }}</h2>

      <div style="display:grid; grid-template-columns: 1fr 200px; gap:12px; align-items:end;">
        <div>
          <label>Nome</label>
          <input v-model="form.name" type="text" placeholder="Fulano" style="width:100%; padding:8px;"/>
        </div>
        <div>
          <label>Data de nascimento</label>
          <input v-model="form.birthDate" type="date" style="width:100%; padding:8px;"/>
        </div>
      </div>

      <div style="margin-top:12px;">
        <button @click="save" :disabled="loading || !form.name || !form.birthDate">
          {{ editingId ? 'Salvar alterações' : 'Adicionar' }}
        </button>
        <button v-if="editingId" @click="cancelEdit" style="margin-left:8px;">Cancelar</button>
      </div>

      <p v-if="error" style="color:#b00; margin-top:8px;">{{ error }}</p>
    </section>

    <section style="border: 1px solid #ddd; padding: 16px; border-radius: 8px; background:#fff;">
      <h2 style="margin-top:0">Lista</h2>
      <p v-if="loading">Carregando...</p>
      <table v-else style="width:100%; border-collapse: collapse;">
        <thead>
          <tr>
            <th style="text-align:left; border-bottom:1px solid #eee;">Nome</th>
            <th style="text-align:left; border-bottom:1px solid #eee;">Nascimento</th>
            <th style="text-align:right; border-bottom:1px solid #eee;">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="p in people" :key="p.id">
            <td>{{ p.name }}</td>
            <td>{{ p.birthDate }}</td>
            <td style="text-align:right;">
              <button @click="startEdit(p)">Editar</button>
              <button @click="remove(p.id)" style="margin-left:8px;">Excluir</button>
            </td>
          </tr>
        </tbody>
      </table>
    </section>
  </div>
</template>

<style>
label { display:block; font-size:12px; color:#555; margin-bottom:4px; }
button { padding:8px 12px; border:1px solid #ccc; background:#f9f9f9; cursor:pointer; border-radius:6px; }
button:disabled { opacity: .6; cursor: not-allowed; }
input { border:1px solid #ccc; border-radius:6px; }
</style>
