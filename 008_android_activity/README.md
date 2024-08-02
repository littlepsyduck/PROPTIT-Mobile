# 008_android_activity

## ACTIVITY VÀ INTENT TRONG ANDROID

- Foreground, background application trong android
- Activity, Context là gì?
- Vòng đời của Activity
- Activity backstack
- Intent, cách sử dụng Intent, phân biệt các loại Intent
- Truyền dữ liệu giữa hai Activity
- Tìm hiểu thêm: Multi-resume, onTopResumedActivityChanged()

- Bài tập : Dựng lại giao diện và thực hiện các yêu cầu trong mô tả của Project.
  - Ứng dụng có 3 màn hình: Đăng ký, đăng nhập, quản lý
  - Khi vào ứng dụng sẽ hiển thị màn hình đăng nhập, nếu chưa có tài khoản thì có thể chuyển sang màn hình đăng ký để đăng ký tài khoản mới.
  - Sau khi đăng ký thành công, sẽ chuyển sang màn hình đăng nhập với tên đăng nhập là số điện thoại đã đăng ký và chờ nhập mật khẩu.
  - Sau khi đăng nhập thành công, sẽ chuyển sang màn hình quản lý với danh sách các thành viên kèm số điện thoại. Khi click vào nút gọi điện thì chuyển sang màn gọi điệnt thoại của máy
  - Giao diện Clone theo thiết kế từ [Figma](https://www.figma.com/design/aTLymfPxxcsUtQAONhLVHL/Activity-Lifecycle?node-id=0-1&t=ANBpbmkvRh1FRSQP-0)
- Lưu ý: Mỗi khi sang một màn khác, các em phải show ra một cái toask hoặc log ra trạng thái của activity màn trước đó và màn hiện tại**

  <img width="1393" alt="Modern Login UI  2 0 (Community)" src="https://github.com/user-attachments/assets/8bb7b823-1883-4e63-afb6-d17c4c8374cf">
