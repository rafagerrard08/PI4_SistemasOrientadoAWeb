

export default {

    adicionaAoCarrinho(produto) {
        for (var i = 0; i < vm.cart.length; i++) {
            if (vm.cart[i].produto.id == produto.id) {
                vm.cart[i].quantidade += 1
                // alert("Aumentado qtd prod " + vm.cart[i].quantidade)
                vm.cartTotal++;
                return;
            }
        }

        const item = { produto: produto, quantidade: 1 };
        vm.cart.push(item);
        vm.cartTotal++;
        // alert("Add novo prod ao cart " + vm.cart.length)

    },

}
