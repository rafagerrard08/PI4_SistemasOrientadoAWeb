
import swal from 'sweetalert2';

export default {

    alertConfirmacaoMid: function(text, confirm = 'Sim', cancel = 'Não', type = 'warning') {
        return swal.fire({   
            type: type,
            text: text,
            showCancelButton: true,
            confirmButtonText: confirm,
            cancelButtonText: cancel,
            buttonsStyling: false,
            confirmButtonClass: 'btn btn-primary mr-1',
            cancelButtonClass: 'btn'
        })
        .then(result => !!result.value)        
      },

      alertConfirmacaoTop: function(text, confirm = 'Sim', cancel = 'Não', type = 'warning') {
        return swal.fire({   
            position: 'top',
            type: type,
            text: text,
            showCancelButton: true,
            confirmButtonText: confirm,
            cancelButtonText: cancel,
            buttonsStyling: false,
            confirmButtonClass: 'btn btn-primary mr-1',
            cancelButtonClass: 'btn'
        })
        .then(result => !!result.value)        
      },

      alertFinalMid: function(text, txtButton = 'Ok', type = "success") {
        swal.fire({
            type: type,
            text: text, 
            showCancelButton: false,
            confirmButtonText: txtButton,
            buttonsStyling: false,
            confirmButtonClass: 'btn btn-primary mr-1',
            cancelButtonClass: 'btn'
        });  
    },

    alertFinalTop: function(text, type, txtButton) {

        const _type = type || 'success';
        const _txtButton = txtButton || 'Ok';

        swal.fire({
            position: 'top',
            type: _type,
            text: text, 
            showCancelButton: false,
            confirmButtonText: _txtButton,
            buttonsStyling: false,
            confirmButtonClass: 'btn btn-primary mr-1',
            cancelButtonClass: 'btn'
        });  
    },

    alertHTML: function(title, body) {

        swal.fire({
            title: `<strong style="font-size: 33px;">${title}</strong>`,
            // type: 'info',
            customClass: 'swal-wide',
            heightAuto: false,
            width: 900,
            html: body,
            focusConfirm: true,

          })
    },

}
