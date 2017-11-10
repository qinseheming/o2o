/**
 * 
 */
$(function() {
	// 从URL里获取shopId参数的值
	var shopId = getQueryString('shopId');
	//判断是进行添加店铺操作还是修改店铺信息操作
	var isEdit = shopId ? true : false;
	if (isEdit) {
		// 编辑店铺前需要获取店铺信息，这里为获取当前店铺信息的URL
		var shopInfoUrl = '/o2o/shopadmin/getshopbyshopid?shopId=' + shopId;
		// 编辑店铺信息的URL
		var editShopUrl = '/o2o/shopadmin/modifyshop';
		getShopInfo(shopId);
	} else {
		// 用于店铺注册时候的店铺类别以及区域列表的初始化的URL
		var initUrl = '/o2o/shopadmin/getshopinitinfo';
		// 注册店铺的URL
		var registerShopUrl = '/o2o/shopadmin/registershop';
		getInitInfo();
	}
	// 通过店铺Id获取店铺信息
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				// 若访问成功，则依据后台传递过来的店铺信息为表单元素赋值
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				// 给店铺类别选定原先的店铺类别值
				var shopCategory = '<option data-id="'
						+ shop.shopCategory.shopCategoryId + '" selected>'
						+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				// 初始化区域列表
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				// 不允许选择店铺类别
				$('#shop-category').attr('disabled', 'disabled');
				$('#area').html(tempAreaHtml);
				// 给店铺选定原先的所属的区域
				$("#area option[data-id='" + shop.area.areaId + "']").attr(
						"selected", "selected");
			}
		});
	}
	function getInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId
							+ '">' + item.shopCategoryName + '</option>'
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>'
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
	}
	$('#submit').click(function() {
		var shop = {};
		if(isEdit){
			shop.shopId = shopId;
		}
		shop.shopName = $('#shop-name').val();
		shop.shopAddr = $('#shop-addr').val();
		shop.phone = $('#shop-phone').val();
		shop.shopDesc = $('#shop-desc').val();
		shop.shopCategory = {
			shopCategoryId : $('#shop-category').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		shop.area = {
			areaId : $('#area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		var shopImg = $('#shop-img')[0].files[0];
		var formData = new FormData();
		formData.append('shopImg', shopImg);
		formData.append('shopStr', JSON.stringify(shop));
		var verifyCodeActual = $('#j_captcha').val();
		if (!verifyCodeActual) {
			$.total('请输入验证码！');
			return;
		}
		formData.append('verifyCodeActual', verifyCodeActual);
		$.ajax({
			url : (isEdit ? editShopUrl : registerShopUrl),
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功');
				} else {
					$.toast('提交失败' + data.errMsg);
				}
				$('#captcha_img').click();
			}
		});
	});
})