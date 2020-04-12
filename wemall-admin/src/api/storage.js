import request from '@/utils/request'

export function uploadCkeck(file) {
	const isJPG = file.type === 'image/jpg' || file.type ==='image/png';
	const isLt2M = file.size / 1024 / 1024 < 2;

	// if (!isJPG) {
	//     that.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
	// }
	// if (!isLt2M) {
	//     that.$message.error('上传头像图片大小不能超过 2MB!');
	// }
	return isJPG && isLt2M;
}


let uploadPath = process.env.BASE_API + '/storage/upload'
export { uploadPath }