

            $(document).ready(function(){
                $(".addcourses1").click(function(){
                    event.preventDefault();	
                    

                    var href = $(this).closest('a').attr('href');
                    console.log(href);	
                    $.get(href, function(country, status) {
                        console.log(country); // Check the entire response object
                        $('#eid').val(country.id);
                        $('#edescription').val(country.description);
                        $('#ecode').val(country.code);
                        $('#ename').val(country.name);
                        $('#ecapital').val(country.capital);
                        $('#econtinent').val(country.continent);
                        $('#enationality').val(country.nationality);
                    }).fail(function(jqXHR, textStatus, errorThrown) {
                        console.error("AJAX request failed: " + textStatus, errorThrown);
                        console.error(jqXHR.responseText); // Log the response from the server
                    });

                    $("#exampleModalCenter").modal('show');
                });
            });

            $(document).ready(function(){
                $(".deletecountry").click(function(){
                    event.preventDefault();	
                    var href = $(this).closest('a').attr('href');
                   $("#delref").attr('href', href);
                    $("#deletemodal").modal('show');
                });
            });

          

            $(document).ready(function(){
                $(".editcategory").click(function(){
                    event.preventDefault();	
                    var href = $(this).closest('a').attr('href');
                    console.log(href);	
                    $.get(href, function(category, status) {
                        console.log(category); // Check the entire response object
                        $('#eid').val(category.id);
                        $('#ename').val(category.name);
                    }).fail(function(jqXHR, textStatus, errorThrown) {
                        console.error("AJAX request failed: " + textStatus, errorThrown);
                        console.error(jqXHR.responseText); // Log the response from the server
                    });
                    $("#editcat").modal('show');
                });
            });

            $(document).ready(function(){
                $(".editproduct").click(function(){
                    event.preventDefault();	
                    var href=$(this).closest('a').attr('href');
                    $.get(href,function(products,status){
                        console.log(products);
                        $("#pid").val(products.id);
                        $('#pname').val(products.name);
                        $('#pcategoryid').val(products.category_id);
                        $('#pprice').val(products.costPrice);
                        $('#pquantity').val(products.currentQuantity);
                        // $('#pimage').val(products.image.substring(products.image.lastIndexOf('/') + 1));
                    });
                    $("#editpro").modal('show');
                });
            });

            $(document).ready(function(){
                $(".deleteproduct").click(function(){
                    event.preventDefault();
                    var href=$(this).closest('a').attr('href');
                    $.get(href,function(products,status){   
                        console.log(products);
                        $("#eid2").val(products.id);
                    });
                    $("#deletepro").modal('show');
                });
            });