const API_URL = '/ktp';

$(document).ready(function() {
    loadKtpData();

    // Handle Form Submit
    $('#ktpForm').on('submit', function(e) {
        e.preventDefault();
        
        const id = $('#ktpId').val();
        const data = {
            nomorKtp: $('#nomorKtp').val(),
            namaLengkap: $('#namaLengkap').val(),
            alamat: $('#alamat').val(),
            tanggalLahir: $('#tanggalLahir').val(),
            jenisKelamin: $('#jenisKelamin').val()
        };

        if (id) {
            updateKtp(id, data);
        } else {
            createKtp(data);
        }
    });

    // Handle Cancel Edit
    $('#btnCancel').on('click', function() {
        resetForm();
    });
});

function loadKtpData() {
    $.ajax({
        url: API_URL,
        type: 'GET',
        success: function(response) {
            let rows = '';
            const data = response.data; // Ambil dari field 'data' di WebResponse
            data.forEach(ktp => {
                rows += `
                    <tr>
                        <td>${ktp.nomorKtp}</td>
                        <td>${ktp.namaLengkap}</td>
                        <td>${ktp.alamat}</td>
                        <td>${ktp.tanggalLahir}</td>
                        <td>${ktp.jenisKelamin}</td>
                        <td class="actions-cell">
                            <button onclick="editKtp(${ktp.id})" class="btn btn-primary btn-sm">Edit</button>
                            <button onclick="deleteKtp(${ktp.id})" class="btn btn-danger btn-sm">Hapus</button>
                        </td>
                    </tr>
                `;
            });
            $('#ktpTableBody').html(rows);
        },
        error: function(err) {
            showNotification('Gagal memuat data!', 'error');
        }
    });
}

function createKtp(data) {
    $.ajax({
        url: API_URL,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function() {
            showNotification('Data KTP berhasil ditambahkan!', 'success');
            resetForm();
            loadKtpData();
        },
        error: function(err) {
            const msg = err.responseJSON ? err.responseJSON.error : 'Terjadi kesalahan!';
            showNotification(msg, 'error');
        }
    });
}

function editKtp(id) {
    $.ajax({
        url: `${API_URL}/${id}`,
        type: 'GET',
        success: function(response) {
            const ktp = response.data; // Ambil dari field 'data'
            $('#ktpId').val(ktp.id);
            $('#nomorKtp').val(ktp.nomorKtp);
            $('#namaLengkap').val(ktp.namaLengkap);
            $('#alamat').val(ktp.alamat);
            $('#tanggalLahir').val(ktp.tanggalLahir);
            $('#jenisKelamin').val(ktp.jenisKelamin);
            
            $('#form-title').text('Update Data KTP');
            $('#btnSubmit').text('Update Data');
            $('#btnCancel').show();
            // Scroll to form
            $('html, body').animate({ scrollTop: 0 }, 'slow');
        }
    });
}

function updateKtp(id, data) {
    $.ajax({
        url: `${API_URL}/${id}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function() {
            showNotification('Data KTP berhasil diperbarui!', 'success');
            resetForm();
            loadKtpData();
        },
        error: function(err) {
            const msg = err.responseJSON ? err.responseJSON.error : 'Gagal memperbarui data!';
            showNotification(msg, 'error');
        }
    });
}

function deleteKtp(id) {
    if (confirm('Apakah Anda yakin ingin menghapus data ini?')) {
        $.ajax({
            url: `${API_URL}/${id}`,
            type: 'DELETE',
            success: function() {
                showNotification('Data KTP berhasil dihapus!', 'success');
                loadKtpData();
            },
            error: function() {
                showNotification('Gagal menghapus data!', 'error');
            }
        });
    }
}

function resetForm() {
    $('#ktpForm')[0].reset();
    $('#ktpId').val('');
    $('#form-title').text('Tambah Data KTP');
    $('#btnSubmit').text('Simpan Data');
    $('#btnCancel').hide();
}

function showNotification(message, type) {
    const notification = $('#notification');
    notification.text(message)
                .removeClass('success error')
                .addClass(type)
                .fadeIn();
    
    setTimeout(() => {
        notification.fadeOut();
    }, 3000);
}
