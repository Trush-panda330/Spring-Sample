package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserAddRequest;
import com.example.demo.dto.UserSearchRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoService;


/**
 * ユーザー情報 Controller
 */
@Controller
public class UserInfoController {

	/**
	 * ユーザー情報 Service
	 */
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * ユーザー情報一覧画面を表示
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@GetMapping("user/list")
	public String displayList(Model model) {
		List<UserInfo> userList = userInfoService.findAll();
		model.addAttribute("userlist", userList);
		model.addAttribute("userSearchRequest", new UserSearchRequest());
		return "user/search";
	}

	/**
	 * ユーザー新規登録画面を表示
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@GetMapping("/user/add")
	public String displayAdd(Model model) {
		model.addAttribute("userAddRequest", new UserAddRequest());
		return "user/add";
	}

	/**
	 * ユーザー新規登録
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */

	@PostMapping("user/create")
	public String create(@Validated @ModelAttribute UserAddRequest userAddRequest,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);

			return "user/add";
		}

		userInfoService.save(userAddRequest);
		return "redirect:/user/list";
	}
	

	/**
	 * ユーザー編集画面を表示
	 * @param id ユーザーID
	 * @param model Model
	 * @return ユーザー編集画面
	 */
	
	@GetMapping("user/{id}/edit")
	//URLのパスパラメータを@PathVariableで受け取りそのidに対応するユーザー情報を取得する
	public String displayEdit(@PathVariable Long id, Model model) {
		//指定されたIDに基づいてユーザー情報をデータベースから取得する
		UserInfo user = userInfoService.findById(id);
		//取得したユーザー情報をUserUpdateRequestにコピー
		UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
		userUpdateRequest.setId(user.getId());
		userUpdateRequest.setName(user.getName());
		userUpdateRequest.setPhone(user.getPhone());
		userUpdateRequest.setAddress(user.getAddress());
		model.addAttribute("userUpdateRequest", userUpdateRequest);
		return "user/edit";

	}

	/**
	 * ユーザー情報検索
	 * @param userSearchRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@PostMapping("user/search")
	public String search(@ModelAttribute UserSearchRequest userSearchRequest,
			Model model) {
		List<UserInfo> userlist = userInfoService.search(userSearchRequest);
		model.addAttribute("userlist", userlist);
		return "user/search";
	}

	//	    /**
	//	     * ユーザー情報削除（論理削除）
	//	     * @param id ユーザーID
	//	     * @param model Model
	//	     * @return ユーザー情報一覧画面
	//	     */
	
			@GetMapping("/user/{id}/delete")
			public String delete(@PathVariable Long id, Model model) {
				userInfoService.delete(id);
				return "redirect:/user/list";
				
			}
	
	//	    @GetMapping("/user/{id}/delete")
	//	    public String delete(@PathVariable Long id, Model model) {
	//	        // ユーザー情報の削除
	//	        userInfoService.delete(id);
	//	        return "redirect:/user/list";
	//	    }
	
	
	/**
	 * ユーザー更新
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return ユーザー情報詳細画面
	 */
	@PostMapping("/user/update")
	//formから送信されたUserUpdateRequestに対してバリデーション処理をする
	//バリデーションの結果はBindingResultに格納される
	public String update(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			//エラーの内容を保持するためのリストを作成
			List<String> errorList = new ArrayList<>();
			//BindingResultに格納されているエラーをループで回し
			for (ObjectError error : result.getAllErrors()) {
				//それぞれのエラーメッセージを取得する
				errorList.add(error.getDefaultMessage());
			}
			//modelにエラーメッセージのリストを渡しビューに渡す
			model.addAttribute("validationError", errorList);
			return "user/edit";
		}
		//バリデーションエラーがない場合
		//ユーザー情報を更新するためのメソッドを呼び出す
		//更新するデータはuserUpdateRequestに入っているので引数で渡している
		userInfoService.update(userUpdateRequest);
		//リダイレクトして一覧画面を表示する
		return "redirect:/user/list";
	}

}